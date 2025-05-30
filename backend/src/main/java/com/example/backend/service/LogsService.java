package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.LogsDAO;
import com.example.backend.model.Logs;
import org.jdbi.v3.core.Jdbi;

public class LogsService {
    private LogsDAO dao;
    public LogsService(Jdbi jdbi) {
        this.dao = jdbi.onDemand(LogsDAO.class);
    }


    public int createLog(Logs logs) {
        return dao.insert(logs.getType(),
                        logs.getCreatedAt(),
                        logs.getOldData(),
                logs.getNewData());
    }


    public static void main(String[] args) {
        LogsService logsService = new LogsService(DBConnection.getJdbi());
        Logs logs = new Logs(null, "INFO", "old data", "new data");

        System.out.println(logsService.createLog(logs));
    }
}
