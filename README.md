# rm-credentials-4-oozie
Grab some remote RM-credentials to submit a MR-Job to a remote cluster.

----

##Problem

Starting a MapReduce job in a different Hadoop-Cluster is required, e.g., to export an HBase snapshot from
cluster A (analysis) to cluster B (backup, inspection, validation ... )

##Solution (Sketch)

Oozie offers the "Credentials" class and configuration to request Delegation Tokens from arbitrary services in each individual action.

----

##Usage

1) Provide the jar file (...) to your Oozie-Server and restart.

2) Define the new Credentials provider class in oozie-...xml (MORE DETAILS)

3) Configure the credentials for your action

