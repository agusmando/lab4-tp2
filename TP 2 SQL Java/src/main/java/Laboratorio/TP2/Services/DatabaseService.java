package Laboratorio.TP2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertCountryInfo(String nombrePais, String capitalPais, String region, long poblacion, double latitud, double longitud) {
        try{
            String sql = "INSERT INTO Pais (nombrePais, capitalPais, region, poblacion, latitud, longitud) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, nombrePais, capitalPais, region, poblacion, latitud, longitud);
        }catch (Error e){
            System.out.println("La longitud de la información supera la capacidad de la celda");
        }
    }
}
