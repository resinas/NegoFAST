NegoFAST
========

NegoFAST is an automated negotiation framework written in Java.

The distribution of NegoFAST includes six parts, which corresponds with the six modules
included in this repository:

1. NegoFAST-Core: It is a set of interfaces that conform the protocol-independent part of 
the NegoFAST framework.
2. NegoFAST-Bargaining: It is a set of interfaces that provide a bargaining-specific
extension of the NegoFAST-Core framework.
3. Model-Lite: It is a set of interfaces that define a specific model for expressing 
agreements, proposals and preferences. Agreements and proposals are modelled as a set of 
name-value pairs, whereas preferences are modelled as weighted utility functions.
4. Simple-Protocol: It is the specification of a simple protocol negotiation protocol and 
a negotiation protocol.
5. August: It is a proof-of-concept implementation of the NegoFAST framework and 
ModelLite. Currently the infrastructure layer of August supports concurrency by means of 
Java threads but it does not support distribution across different computers. However, 
this infrastructure layer is easily replaceable by a more complex one.
6. JobsSubmitter: It is a sample implementation of an automated negotiation system based 
on the NegoFAST framework. It implements the negotiation of a job submission agreement 
amongst a job submitter and two job hosting services.

You can try the demo implementation of the JobsSubmitter by running:

	mvn exec:java -pl demo-jobs-submitter

We are very interested in any feedback you might have, including details of how you're 
using NegoFAST, what you like about it, and what you think needs improvement. You can 
leave this feedback at resinas <at> us.es.
