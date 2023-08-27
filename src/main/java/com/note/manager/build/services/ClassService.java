package com.note.manager.build.services;

import com.note.manager.build.Utils.ErrorMessage;
import com.note.manager.build.model.Class;
import com.note.manager.build.repository.ClassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    ClassRepository repository;

    public List<Class> findAll(){
        List<Class> classList = new ArrayList<>();
        try(PreparedStatement statement = this.repository.findAll()) {
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Class aClass = new Class(
                        result.getLong("id"),
                        result.getString("name")
                );
                classList.add(aClass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classList;
    }

    public Optional<Class> findByName(String name){
        try(PreparedStatement statement = this.repository.findByName(name)) {
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return Optional.of(
                    new Class(
                        result.getLong("id"),
                        result.getString("name")
                    )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Object saveClass(Class aClass){
        try( PreparedStatement statement = this.repository.saveClass(aClass) ) {
            int insertRow = statement.executeUpdate();
            if (insertRow > 0) return aClass;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Missing value class",
                aClass
        );
    }

    public Object updateName(String oldName,String newName){
        HashMap<String,String> updates = new HashMap<>();
        updates.put("OldName",oldName);
        updates.put("newName",newName);

        try(
                PreparedStatement statement = this.repository
                .updateClassNameByOldName(oldName,newName)
        ) {
            boolean result = statement.executeUpdate() > 0;
            if (result){
                return updates;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Missing old value or new value",
                updates
        );
    }

    public Object deleteByName(String name){
        try(PreparedStatement statement = this.repository.deleteByName(name)) {
            boolean isDeleted = statement.executeUpdate() > 0;
            if (isDeleted){
                return String.format("class: %s deleted successfully",name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "missing name of class to delete",
                name
        );
    }
}
