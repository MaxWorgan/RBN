RBN{
	var nodes,links,k,numNodes;
	*new {
		^super.new
	}
	init { | anumNodes, ak |
		numNodes = anumNodes;
		k = ak;
		nodes = Array.fill(numNodes, {RBNNode.new.init(k)});
		links = Array.fill(numNodes,0);
		this.createNetwork;
	}
	
	createNetwork{
		numNodes.do({|n|
			links.put(n,{rand(numNodes)}.dup(k));
		});
	}
	//using a classical updating scheme
	step{
		//iterate over all nodes
		nodes.do({|n,i|
			//get status of each conncting node
			//and put in to inputs array ( but don't change status yet)
			n.k.do({|m|
				n.inputs.put(m, nodes[links[i][m]].status);
			});
		});
		
		//now interate again and update status
		nodes.do({|n| n.step; })
		^nodes.collect({|n| n.status});
	}
	
	printOn { arg stream;
		nodes.do({|n|
			n.post;
		});
� � }
}

		