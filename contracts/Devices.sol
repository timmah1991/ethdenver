pragma solidity ^0.4.4;

contract Devices{
	
	uint public percentage_available;

	function deposit() payable {
	 	
	 }
	
	function setCurrentStatus(uint x) public{
		percentage_available=x;


	 }
	function getCurrentStatus() constant returns(uint){
		return percentage_available;
	 }


}