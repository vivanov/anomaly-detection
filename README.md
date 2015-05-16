Anomaly Detection using Multivariate Gaussian Distribution
====================

Anomaly Detection example using _Multivariate Gaussian Distribution_ and _Apache Spark MLlib_

Description
-----------

This is rather simplistic example of Anomaly Detection algorithm using Multivariate Gaussian Distribution. It calculates mu vector and sigma2 matrix from data set and passes them as parameters to Spark MLlib MultivariateGaussian to get probability density for each data vector. Then using cross validation data set it tries to find best epsilon value with F1 score metric. Having best epsilon value it finds out outliers and prints results.

Application takes four parameters:

1) Path to data file: mxn matrix containing m examples where each row is a n-dimensional vector of features
2) Path to file containing cross validation data set where each row is a n-dimensional vector of features
3) Path to file containing explicitly assigned result for each row of the cross validation data set above, where 0 considered normal data and 1 anomaly
4) Path to the output directory

Output:

Application creates three subdirectories under output directory (specified as an input parameter):

- `/ps` contains probability density value for each data vector
- `/eps_f1` contains pairs of epsilon value and corresponding F1 score
- `/outliers` contains actual output of the algorithm: index, data vector and probability density value for each detected anomaly

Notes
-----------

- It's allowed to put comment lines that should start with `#` symbol and empty lines into data files

Example Input and Output
------------------------

### Input 

#### Data

```
 15.63593869777692 14.53784766638118
 13.12046241712303 15.47794525058771
 14.65489847052287 15.08339526603583
```

#### Cross validation data and assigned flags

```
 14.63535869776363 15.73463454522354 0
 21.23424241716346 11.63463435423546 1
 15.84705234224234 13.02402940942027 0
```

### Output

#### Outliers

```
(2 ,([25.24204234224854,18.24237567943457],4.374744745738043E-6))
(11,([19.83405277224942,10.19444940942003],5.745672810153831E-8))
```