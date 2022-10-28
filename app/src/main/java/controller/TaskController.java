package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO tasks (idProject, name, description, "
                + "completed, notes, deadline, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Create of conection with the database
            connection = ConnectionFactory.getConnection();
            
            //Preparing the query
            statement = connection.prepareStatement(sql);
            
            //Setting the values
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            //Executing the SQL for insertion of data
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a terefa"
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);

        }
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET idProject = ?, name = ?, "
                + "description = ?, notes = ?, deadline = ?, "
                + "completed = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            
            //Create of conection with the database
            connection = ConnectionFactory.getConnection();
            
            //Preparing the query
            statement = connection.prepareStatement(sql);
            //Setting the values
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
        
            //Executing the SQL for insertion of data
            statement.execute();
            
        } catch (Exception ex){
            throw new RuntimeException("Erro ao atualizar a tarefa");
        }

    }

    public void remove(Task task) {

    }

    public void removeById(int taksId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Create of conection with the database
            connection = ConnectionFactory.getConnection();
            
            //Preparing the query
            statement = connection.prepareStatement(sql);
            
            //Setting the values
            statement.setInt(1, taksId);
            
            //Executing the query
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        //Class that will recover the data of DB.
        ResultSet resultSet = null;
        
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            
            //Create of conection
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //Setting the values that match the search filter
            statement.setInt(1, idProject);
            statement.executeQuery();
            
            //While exist data in the base, do
            while(resultSet.next()){
                
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                //Add the recovery contact 
                tasks.add(task);
                
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //Tasks list that was created and loaded of Database
        return tasks;
    }

}
