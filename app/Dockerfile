FROM mysql
ADD script.sql /docker-entrypoint-initdb.d/script.sql
RUN chmod -R 775 /docker-entrypoint-initdb.d

ENV MYSQL_RANDOM_ROOT_PASSWORD yes