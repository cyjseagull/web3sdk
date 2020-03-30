pragma solidity ^0.4.4;
import "./Evidence.sol";

contract EvidenceTest
{
    event newEvidenceEvent(address addr);
    function insertEvidence(string evi, string info, string id) public returns(address)
    {
        address evidence = new Evidence(evi, info, id);
        newEvidenceEvent(evidence);
    }
}
