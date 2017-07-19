# SDG-Caching-Example
This project provides a sample implementation of spring caching abstraction using Pivotal Gemfire.

### STEP 1: Start a Gemfire Cluster

For Convenience, scripts are provided to Start a Gemfire Cluster. Navigate to **Grid** folder and update the environment variables in gf.config file.

Start the locator by running **startLocator.sh** and Start the cache servers by running **startServer.sh**

### STEP 2: Create the Gemfire Region Account

```
create region --name=Account --type=PARTITION_REDUNDANT --entry-idle-time-expiration=20 --entry-idle-time-expiration-action=INVALIDATE --enable-statistics=true
```

### STEP 3: Run the Demo

```
mvn spring-boot:run
```
