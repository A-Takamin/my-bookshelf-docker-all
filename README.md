# my-bookshelf-docker-all

## 概要
my-bookshelfシリーズのfront, back, dbをすべてDocker化したリポジトリです。  
いずれはこちらを主体にしていきます。

## ※注意
2021/2/19日現在、まだ完全に移行できていません。  
例えば、コードのベタ書きが多く、環境変数などを用意する必要があります。

## 環境構築手順
#### 1. docker, docker-composeのインストール
[Dockerのインストール](https://docs.docker.com/get-docker/)  
[Docker Composeのインストール](https://matsuand.github.io/docs.docker.jp.onthefly/compose/install/)

#### 2. Firebaseプロジェクトの作成
1. Firebaseでプロジェクトを作成し、"firebaseConfig"の値をすべてメモする。  
1. frontendディレクトリに".env.local"という名前のファイルを作成する。  
1. ".env"ファイルのキーを".env.local"にコピーし、先ほどメモしていたConfigの値を入れていく。  
参考：[Qiita: Firebaseプロジェクト作成方法](https://qiita.com/yoshi0518/items/25af102845ba05545f98)
