package com.abhishek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootApplication
@Controller
public class StartApplication {

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("title", "🚀 DevOps CI/CD Pipeline Dashboard");
        model.addAttribute("appName", "Spring Boot DevOps Demo");
        model.addAttribute("version", "v2.1.0");
        model.addAttribute("buildNumber", "#" + (new Random().nextInt(999) + 100));
        model.addAttribute("deploymentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        // Pipeline stages with status and duration
        List<Map<String, Object>> pipelineStages = Arrays.asList(
            createStage("Source Code", "📋", "GitHub Webhook Trigger", "SUCCESS", "2s", "fa-github"),
            createStage("Build & Test", "⚙️", "Jenkins + Maven Build", "SUCCESS", "45s", "fa-cogs"),
            createStage("Code Quality", "🔍", "SonarQube Analysis", "SUCCESS", "30s", "fa-search"),
            createStage("Security Scan", "🛡️", "Dependency Check", "SUCCESS", "15s", "fa-shield-alt"),
            createStage("Docker Build", "🐳", "Container Image Build", "SUCCESS", "25s", "fa-docker"),
            createStage("Image Push", "☁️", "Push to DockerHub", "SUCCESS", "20s", "fa-cloud-upload-alt"),
            createStage("Deploy", "🎯", "Argo CD Deployment", "SUCCESS", "40s", "fa-rocket"),
            createStage("Health Check", "❤️", "Kubernetes Readiness", "SUCCESS", "10s", "fa-heartbeat")
        );
        
        model.addAttribute("pipelineStages", pipelineStages);
        
        // System metrics
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("cpu", "23%");
        metrics.put("memory", "512MB / 2GB");
        metrics.put("pods", "3/3 Running");
        metrics.put("uptime", "15d 4h 32m");
        model.addAttribute("metrics", metrics);
        
        // Recent deployments
        List<Map<String, Object>> deployments = Arrays.asList(
            createDeployment("v2.1.0", "Production", "SUCCESS", "2 minutes ago"),
            createDeployment("v2.0.5", "Staging", "SUCCESS", "1 hour ago"),
            createDeployment("v2.0.4", "Development", "SUCCESS", "3 hours ago")
        );
        model.addAttribute("deployments", deployments);
        
        // Environment info
        Map<String, String> environment = new HashMap<>();
        environment.put("cluster", "k8s-prod-cluster");
        environment.put("namespace", "devops-demo");
        environment.put("node", "worker-node-01");
        environment.put("region", "us-west-2");
        model.addAttribute("environment", environment);
        
        return "index";
    }
    
    @GetMapping("/api/health")
    @ResponseBody
    public Map<String, Object> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("version", "v2.1.0");
        health.put("environment", "production");
        return health;
    }
    
    @GetMapping("/api/metrics")
    @ResponseBody
    public Map<String, Object> getMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        Random rand = new Random();
        metrics.put("requests_per_minute", rand.nextInt(100) + 50);
        metrics.put("response_time_ms", rand.nextInt(50) + 10);
        metrics.put("error_rate", "0.01%");
        metrics.put("availability", "99.99%");
        return metrics;
    }
    
    private Map<String, Object> createStage(String name, String emoji, String description, String status, String duration, String icon) {
        Map<String, Object> stage = new HashMap<>();
        stage.put("name", name);
        stage.put("emoji", emoji);
        stage.put("description", description);
        stage.put("status", status);
        stage.put("duration", duration);
        stage.put("icon", icon);
        return stage;
    }
    
    private Map<String, Object> createDeployment(String version, String environment, String status, String time) {
        Map<String, Object> deployment = new HashMap<>();
        deployment.put("version", version);
        deployment.put("environment", environment);
        deployment.put("status", status);
        deployment.put("time", time);
        return deployment;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
