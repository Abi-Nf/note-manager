# note-manager

This project aims to make an API for HEI's students notes

## The MCD

![Mcd image](./mcd-note-manager.png)

## Usages

### Postgresql connection configuration

-> To proceed for connection, we need to pass all values in environment variables

- On windows

```shell
setx PSQL_URL 'jdbc:postgresql://[host-here]:[port-here]/[database-name-here]' # The database name should exists
setx PSQL_USER 'your postgresql user'
setx PSQL_PASS 'your postgresql password'
```

- On Linux

```shell
export PSQL_URL="jdbc:postgresql://[host-here]:[port-here]/[database-name-here]" # The database name should exists
export PSQL_USER="your postgresql user"
export PSQL_PASS="your postgresql password"
```

### model entities

Unfortunately, models won't be updated or created by magics.

```shell
manager --init
```

### Mock data

if you need mock data, there is just one command to execute

```shell
manager --push
```