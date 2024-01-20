package com.transaction.practice.handler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.stream.Stream;

@AllArgsConstructor
@Log4j2
public class JdbcInvocationHandler implements InvocationHandler {
    private Connection connection;



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        if(Stream.of("commit","close","rollback").anyMatch(s -> s.equals(method.getName())))
            log.info("method called Connection::{}",method.getName());
        return   method.invoke(connection,args);

    }
}
