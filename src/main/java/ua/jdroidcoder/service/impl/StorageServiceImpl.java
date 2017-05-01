package ua.jdroidcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.jdroidcoder.config.StorageProperties;
import ua.jdroidcoder.persistent.dto.OrderDto;
import ua.jdroidcoder.persistent.entity.OrdersEntity;
import ua.jdroidcoder.service.OrderService;
import ua.jdroidcoder.service.StorageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by jdroidcoder on 18.04.17.
 */
@Service
public class StorageServiceImpl implements StorageService {
    private final Path rootLocation;
    private OrderService orderService;

    @Autowired
    public StorageServiceImpl(StorageProperties properties, OrderService orderService) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.orderService = orderService;
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IOException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new MalformedURLException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                List<OrdersEntity> orderDtos = orderService.getOrders();
                for (int i = 0; i < orderDtos.size(); i++) {
                    Date date = orderDtos.get(i).getCreatedDate();
                    Date currentDate = new Date();
                    if (currentDate.getTime() - date.getTime() >= 1800000) {
                        orderService.removeOrderById(orderDtos.get(i).getId());
                    }
                }
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
