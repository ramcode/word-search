## How to run code:

Install Java if not installed already

```bash
brew install java
```

## Download jar file to local machine

```html
Link to jar: https://github.com/ramcode/word-search/blob/main/target/word-search-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Run jar file with input files as params

```java
java -jar <jar_file> <predefined_words_file_location> <input_file_location>
ex: java -jar word-search-1.0-SNAPSHOT-jar-with-dependencies.jar /users/test/predefined_words.txt /users/test/input_file.txt
```
## Program outputs word match count to console

```bash
          Predefined word               Match count
                     Name                        2
                   Detect                        0
                       AI                        1
```

