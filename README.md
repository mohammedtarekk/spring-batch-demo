# Spring Batch Demo

## Use Cases
- Automated, complex processing of large volumes of information that is most efficiently processed without user interaction. These operations typically include time-based events (such as month-end calculations, notices, or correspondence).
- Periodic application of complex business rules processed repetitively across very large data sets (for example, insurance benefit determination or rate adjustments).
- Integration of information that is received from internal and external systems that typically requires formatting, validation, and processing in a transactional manner into the system of record. Batch processing is used to process billions of transactions every day for enterprises.
---
## Business Scenarios
    - Commit batch process periodically.
    - Concurrent batch processing: parallel processing of a job.
    - Staged, enterprise message-driven processing.
    - Massively parallel batch processing.
    - Manual or scheduled restart after failure.
    - Sequential processing of dependent steps (with extensions to workflow-driven batches).
    - Partial processing: skip records (for example, on rollback).
    - Whole-batch transaction, for cases with a small batch size or existing stored procedures or scripts.
---
## Implementation Notes
### Chunk-oriented Processing
Spring Batch uses a “chunk-oriented” processing style in its most common implementation. Chunk oriented processing refers to reading the data one at a time and creating 'chunks' that are written out within a transaction boundary. Once the number of items read equals the commit interval, the entire chunk is written out by the ItemWriter, and then the transaction is committed.

Chunking increases the performance by reducing the number of transactions and database operations. By grouping multiple items into one chunk, you can write them out in one transaction instead of creating a new transaction for each item. This reduces the overhead of transaction management and improves the throughput. Also, by using a batch size that matches the chunk size, you can execute a single SQL statement for multiple inserts instead of executing a separate statement for each item. This reduces the network and database load and improves the efficiency.

---
### What is the difference between chunk size and batch size?
Chunk size and batch size are two different concepts in Spring Batch. Chunk size is the number of items that are read and processed before writing them out in a transaction. Batch size is the number of items that are written out in one SQL statement. Chunk size is configured by the `chunk()` method of the `StepBuilder` class, while batch size is configured by the `spring.jpa.properties.hibernate.jdbc.batch_size` property in the application.properties file. Ideally, chunk size and batch size should match for optimal performance.

---
### Using TaskExecutor in Step implementation
taskExecutor is a bean that implements the TaskExecutor interface from Spring Framework. It is used to execute tasks asynchronously in a multi-threaded environment. By adding a taskExecutor to your step configuration, you can enable parallel processing of chunks within a single step. This can increase performance by utilizing multiple cores and reducing the waiting time for I/O operations.

---
