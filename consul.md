# Consul production setup guide line
 - [Consul Deployment guideline by Digital Ocean](https://www.digitalocean.com/community/tutorials/how-to-configure-consul-in-a-production-environment-on-ubuntu-14-04)
 - [Consul Deployment Guyide line By Hashicorp](https://learn.hashicorp.com/consul/datacenter-deploy/deployment-guide)
 - [download consul](https://www.consul.io/downloads)

## To run agent locally, which should join consul server 
consul agent -data-dir=/consul/data -config-dir=/consul/config -advertise=<IP_OF_AGENT_MACHINE> -retry-join=<CONSUL_SERVER:PORT> -client=0.0.0.0 -node=inlubt0081.iind.intel.com -bind=<IP_OF_AGENT_MACHINE> -datacenter=<DATA_CENTER> -raft-protocol=3
