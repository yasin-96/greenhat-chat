FROM confluentinc/cp-kafka-connect
ENV CONNECT_PLUGIN_PATH="/usr/share/java,/usr/share/confluent-hub-components"
RUN confluent-hub install --no-prompt mongodb/kafka-connect-mongodb:1.3.0
RUN confluent-hub install --no-prompt hpgrahsl/kafka-connect-mongodb:1.4.0
