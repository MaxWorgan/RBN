RBN{
	var nodes,links,numNodes;
	*new {
		^super.new
	}
	// number of nodes in network, k is mean number of connects, p is probablity of each boolean function
	init { | numberOfNodes, k , p |
		numNodes = numberOfNodes;
		nodes = Array.fill(numNodes, {RBNNode.new.init(k,p)});
		links = Array.fill(numNodes,0);
		this.createNetwork;
	}
	
	createNetwork{
		nodes.do({|n,i|
			links.put(i, {rand(numNodes)}.dup(n.k));
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
Ê Ê }
}

		