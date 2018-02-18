pragma solidity ^0.4.4;

contract Utility{

	bool public _switch=true;

	event Sent(address from, address to, uint amount);
	mapping (address => uint) public balances; 

 	function deposit(uint x) {
 		balances[tx.origin]+=x;
	 	
	 }

	 function getAddress() returns(address){
	 	return msg.sender;
	 }

	function getBalance() constant returns(uint){
	 	return balances[msg.sender];
	 }  

	function sendfund(address _to, uint value) payable {
		if (balances[msg.sender] < value){
			_switch=false;
		}
		else{
        balances[msg.sender] -= value;
        balances[_to] += value;
        Sent(msg.sender, _to, value);
    	}
	}


}

