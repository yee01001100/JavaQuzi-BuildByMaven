package com.yee.quiz.service;

import java.util.List;
import java.io.InputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.yee.quiz.model.*;

public class DataService {

    private static final String usersJsonPath = "src/main/resources/users.json";

    //加载users.json文件
    public static List<User> loadUsersFromJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = User.class.getClassLoader().getResourceAsStream("users.json");

            //处理users.json文件缺失
            if(inputStream == null){
                throw new RuntimeException("无法找到users.json文件");
            }

            List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>(){});
            inputStream.close();
            return users;
        }catch (Exception e){
            throw new RuntimeException("读取users.json失败："+e.getMessage(),e);
        }
    }

    //加载questions.json文件
    public static List<Question> loadQuestionsFromJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Question.class.getClassLoader().getResourceAsStream("questions.json");

            //处理questions.json文件缺失
            if(inputStream == null){
                throw new RuntimeException("无法找到questions.json文件");
            }

            List<Question> questions = objectMapper.readValue(inputStream, new TypeReference<List<Question>>(){});
            inputStream.close();
            return questions;
        }catch (Exception e){
            throw new RuntimeException("读取questions.json失败："+e.getMessage(),e);
        }
    }

    //加载quizLog.json文件
    public static List<QuizLog> loadQuizLogsFromJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Question.class.getClassLoader().getResourceAsStream("quizLog.json");

            //处理quizLog.json文件缺失
            if(inputStream == null){
                throw new RuntimeException("无法找到quizLog.json文件");
            }

            List<QuizLog> quizLogs = objectMapper.readValue(inputStream, new TypeReference<List<QuizLog>>(){});
            inputStream.close();
            return quizLogs;
        }catch (Exception e){
            throw new RuntimeException("读取quizLog.json失败："+e.getMessage(),e);
        }
    }

    //判断用户是否存在
    public static boolean isUsernameExist(String username){
        for(User user : loadUsersFromJson()){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    //保存新用户至users.json
    public static void saveUsersToJson(List<User> users){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(usersJsonPath);

            //如果程序运行过程文件丢失防崩溃
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            //核心写入逻辑
            FileWriter fileWriter = new FileWriter(file);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, users);
            fileWriter.close();
        }catch (IOException e){
            throw new RuntimeException("保存users.json失败："+e.getMessage(),e);
        }
    }

    //新用户注册
    public static void registerUser(String username, String password){
        List<User> users = loadUsersFromJson();

        String newId = String.valueOf(users.size()+1);
        User newUser = new User(newId, "student", username, password);
        users.add(newUser);

        saveUsersToJson(users);
    }
}