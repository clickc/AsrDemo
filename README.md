demo and api of cnn+lstm+ctc english Automatic Speech Recognition

java实现cnn+lstm+ctc 深度学习网络的构建，wfst解码，用于英文语音识别，common voice语料(30万训练样本) 作为训练集训练模型。timit语料作为测试集，准确率0.93，相同测试集，百度英文语音识别(800万训练样本)准确率0.98； voxforge语料作为测试集，准确率0.92，相同测试集，百度英文语音识别(800万训练样本)准确率0.95。cnn+lstm+ctc 部分完全是java实现，不需要用到GPU。识别速度是百度语音识别的3-4倍。使用多线程随机梯度下降方法训练模型，30万训练样本大约需要一天时间训练完成。

build:

  wget https://dlcdn.apache.org//ant/binaries/apache-ant-1.9.16-bin.zip
  unzip apache-ant-1.9.16-bin.zip

  cd AsrDemo
  ant(或者/usr/local/apache-ant-1.9.16/bin/ant)

  生成的jar包位于out文件夹中

run:
 java -cp out/asrdemo.jar AsrDemo



  


