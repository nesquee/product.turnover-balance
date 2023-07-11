FROM openjdk:17-alpine

COPY ./lastversion/*.jar app/

ENTRYPOINT ["java", "-jar", "app/turnover-balance.jar", "com.abelyaev.turnoverbalance.TurnoverBalanceApplication"]