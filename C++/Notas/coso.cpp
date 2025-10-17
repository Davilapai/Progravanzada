for(int i = 1; i <= n; i++){
        bool found = false;

        for(int j = 0; j < n; j ++){

            if(strategies[j].num_strategy == i){
            found = true;
            break;
        }
        }

        if(!found){
            cout << i << " "; 
        }
    }