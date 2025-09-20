# ADD docker-compose.yml file in the root directory
# pull this repository and the https://github.com/preetyadav420/booking-rental-ui.git repo in the root directory
# docker-compose up --build  // run in the root directory
# Access the application on http://localhost:3000/


services:
  db:
    image: postgres:15
    container_name: postgres_db
    environment:
      POSTGRES_DB: booking-rental
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: preet7551
    ports:
      - "5432:5432"
    volumes:
      - db_data:/var/lib/postgresql/data

  booking-rental:
    build: ./booking-rental
    container_name: booking-rental
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/booking-rental?createIfNotExist
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: preet7551
    ports:
      - "8080:8080"
    depends_on:
      - db

  booking-rental-ui:
    build: ./booking-rental-ui
    container_name: booking-rental-ui
    ports:
      - "3000:80"
    depends_on:
      - booking-rental

volumes:
  db_data:
