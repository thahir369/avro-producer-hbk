# avro-producer-hbk

This is simple producer application which publishes data to topic in confluent kafka.
As we are using confluent kafka, We can define a schema and publish data.

Here we can update avro and it will be considered as version 2. We can publish version 2 payload as well.

But while consuming data of mutiple versions we face some difficulty. So I have removed version 2 endpoint
and made single end point for version 1.