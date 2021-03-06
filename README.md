# Home task - SuffixingApp With Maven

Task is to use maven to build and control a simple Java program with dependencies

## Details:
    * Application should read a config file on the startup
    * Then it should ensure that all of files from the config exist
    * Then it should rename each file adding a suffix from the config to its name
    * It should print the results of the renaming like: *old_name -> new_name*

## Logging Specification

    * Application should log startup information.
    * Application should log information on config read.
    * Application should log renaming process information.
    * Application should log summary information.
    * Application should log shutdown information.
    * Application should handle and log possible errors.
    * Use different logging level. 
    * All log entries should contain a date and time information as well.

## Steps
    1. Create a maven project and specify its GAV settings, encoding, language level, etc.
    2. Add a dependency to some library for reading and parsing JSON files. (for instance, GSON)
    3. Write the code implementing the app specification.
    4. Configure maven project to build a runnable jar containing application and its dependencies.
    5. ???
    6. PROFIT!

