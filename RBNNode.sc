RBNNode {
	var <status, <k,func,<>inputs, <lookup;
	*new {
		^super.new
	}
	
	init { arg nk = 2.5;
		var powNum;
		k = nk.floor;			  	//set k - num of connections
		if(nk.frac.coin, {k=k+1},{}); // allow connection probabilities
		powNum = 2.pow(k);
		status = 2.rand; 		  //randomly alive or dead
		func = Array.rand(powNum,0,1);//generate random boolean function
		inputs = Array.fill(k,0);	  //setup inputs

		//create lookup table
		lookup = Array.fill2D(k, powNum,0);
		lookup.put(0,powNum.asInteger.collect({|i| i.mod(2)})); // fill first column with 0,1,0,1...
		(k-1).do({|n|
			lookup.put(n+1, lookup.at(n).stutter.reshape(powNum.asInteger)); //stutter and tuncate all others
		});
		lookup = lookup.flop; // transpose
	}
	
	step{
		var index = lookup.detectIndex({|x| x==inputs});
		status = func[index];
	}
	printOn { arg stream;
		if(status == 0, {stream <<"-";},{stream << "#"});
Ê Ê }

}