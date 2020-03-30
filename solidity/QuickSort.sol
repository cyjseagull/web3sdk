pragma solidity ^0.4.0;

contract QuickSort {
    event finish(uint size);
    function sort(uint size) {
        uint[] memory data = new uint[](size);
        for (uint x = 0; x < data.length; x++) {
            data[x] = size-x;
        }
        quickSort(data, 0, data.length - 1);
        finish(size);
    }
    
    function quickSort(uint[] arr, uint left, uint right) internal {
        uint i = left;
        uint j = right;
        if (i == j) return;
        uint pivot = arr[left + (right - left) / 2];
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (pivot < arr[j]) j--;
            if (i <= j) {
                uint tmp = arr[i];
                arr[i] =  arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        if (left < j && i > 1)
            quickSort(arr, left, i - 1);
        if (i < right)
            quickSort(arr, i + 1, right);
    }
}
