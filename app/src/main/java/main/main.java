package main;

import controller.ProjectController;
import java.sql.Connection;
import model.Project;
import util.ConnectionFactory;

public class main {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        
        
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("Projeto Teste");
        project.setDescription("description");
        projectController.save(project);
    }
    
    
}
