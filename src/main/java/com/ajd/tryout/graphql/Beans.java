package com.ajd.tryout.graphql;

import com.ajd.tryout.graphql.entity.Employee;
import com.ajd.tryout.graphql.service.EmployeeService;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.List;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Configuration
public class Beans {

    @Bean("FilteredEmployee")
    public DataFetcher<List<Employee>> getFilteredEmployeesDataFetcher(EmployeeService employeeService) {
        return dataFetchingEnvironment -> {
            String gender = dataFetchingEnvironment.getArgument("gender");
            int yoj = dataFetchingEnvironment.getArgument("yoj");
            // int yoj = Integer.parseInt(yojs);
            return employeeService.getFilteredEmployee(gender, yoj);
        };
    }


    @Bean
    public RuntimeWiring buildWiring(EmployeeService employeeService) {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                .dataFetcher("employees", getFilteredEmployeesDataFetcher(employeeService)))
                .build();
    }


    @Bean
    public GraphQLSchema buildSchema(EmployeeService employeeService) {
        GraphQLSchema graphQLSchema = null;

        try {
            URL url = Resources.getResource("schema.graphqls");
            String sdl = Resources.toString(url, Charsets.UTF_8);
            TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
            RuntimeWiring runtimeWiring = buildWiring(employeeService);
            SchemaGenerator schemaGenerator = new SchemaGenerator();
            graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return graphQLSchema;
    }
}
