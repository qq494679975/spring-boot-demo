application: Application name, if unset it will be inferred from the application.name system property
hostname: Host name, if unset it will be inferred from the box host name via InetAddress.getLocalHost().getHostName()
type: Type of logging, will be ignored if unset
flumeAgents: Comma separated list of flume avro agents in format {hostname}:{port}
flumeProperties: Additional properties to create the flume RpcClient in format {key}={value} separated by ;
batchSize: Set the batch size of events to report, overriding the default of 50
reportingWindow: Override the maximum time spent trying to fill a batch, between 100ms and 10s
additionalAvroHeaders: Add additional headers to the events in format {key}={value} separated by ;
reporterMaxThreadPoolSize: Set the maximum number of threads for the reporter to use, overriding the default 2
reporterMaxQueueSize: Set the maximum number of event batches to keep in memory, overriding the default 50

git地址
https://github.com/gilt/logback-flume-appender