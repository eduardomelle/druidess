
# Elk

Implementação de logs centralizados para aplicações Spring Boot utilizando as ferramentas ELK e a biblioteca Logback.
## Instalação

Criando o diretório de volume do ElasticSearch:

```bash
sudo mkdir -p /docker/elastic/data
sudo chmod -R g+rwx /docker/elastic
sudo chown -R 1000:1000 /docker/elastic
```

```bash
sudo sysctl -w vm.max_map_count=262144
```

Criando uma rede no Docker para as ferramentas ELK:

```bash
docker network create elk
```

Definindo a versão as ferramentas ELK:

```bash
VERSAO=7.13.1
export VERSAO
```

Criando o container do ElasticSearch:

```bash
docker container run -d --name elasticsearch --network elk -p 9200:9200 -p 9300:9300 --restart always -e "http.host=0.0.0.0" -e "discovery.type=single-node" -v /docker/elastic/data/:/usr/share/elasticsearch/data elasticsearch:$VERSAO
```

Criando o container do Kibana:

```bash
docker container run -d --name kibana --network elk -p 5601:5601 --restart always -e "ELASTICSEARCH_HOSTS=http://elasticsearch:9200" -e XPACK_GRAPH_ENABLED=true -e XPACK_WATCHER_ENABLED=true -e XPACK_ML_ENABLED=true -e XPACK_MONITORING_ENABLED=true -e XPACK_MONITORING_UI_CONTAINER_ELASTICSEARCH_ENABLED kibana:$VERSAO
```

Criando o container do Logstash:

```bash
docker container run -d --name logstash --network elk --restart=always -e "XPACK.MONITORING.ELASTICSEARCH.HOSTS=http://elasticsearch:9200" logstash:$VERSAO
```

Listando os índices criados pelo ElasticSearch:

```bash
http://localhost:9200/_cat/indices
```
