# Commands for mssql

```bash
docker exec -it mssql "bash" 
/$ /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P {password}
```

### For check database
```bash
SELECT TABLE_NAME FROM memento_mori.INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'
```