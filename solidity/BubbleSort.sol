pragma solidity ^0.4.4;
contract BubbleSort
{
    event finish(uint size);
    function sort(uint size) {
        uint[] memory data = new uint[](size);
        for (uint x = 0; x < data.length; x++) {
            data[x] = size-x;
        }
        bubbleSort(data, data.length);
        finish(size);
    }
    
    function bubbleSort(uint[] _data, uint _dataLen) internal {
        for(uint i = 0; i < (_dataLen - 1); i++)
        {
            for(uint j = i + 1; j < (_dataLen - 1); j++)
            {
                if(_data[i] > _data[j])
                {
                    uint tmp = _data[i];
                    _data[i] = _data[j];
                    _data[j] = tmp;
                }
            }
        }
    }
}