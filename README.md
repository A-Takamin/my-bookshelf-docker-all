# my-bookshelf-docker-all

## 概要
my-bookshelfシリーズのfront, back, dbをすべてDocker化したリポジトリ。  

## ToDo
- ログ
- 本推薦機能追加
- 本IDや本ジャンルなどを追加
- ユーザーネーム追加
- SNSログイン追加
- その他諸々。

## 環境構築手順
#### 1. docker, docker-composeのインストール
```
docker version 
docker-compose version
```
出力がなければインストール  

参考：[Dockerのインストール](https://docs.docker.com/get-docker/)  
参考：[Docker Composeのインストール](https://matsuand.github.io/docs.docker.jp.onthefly/compose/install/)

#### 2. Firebaseプロジェクトの作成
1. Firebaseでプロジェクトを作成し、"firebaseConfig"の値をすべてメモする。  
1. frontendディレクトリに".env.local"という名前のファイルを作成する。  
1. ".env"ファイルのキーを".env.local"にコピーし、先ほどメモしていたConfigの値を入れていく。  
1. firebaseプロジェクトのAuthenticationで、メールとパスワードによる認証をONにしておく。
  
参考：[Qiita: Firebaseプロジェクト作成方法](https://qiita.com/yoshi0518/items/25af102845ba05545f98)

#### 3. 起動
ビルド
```
docker-compose build
```

起動
```
docker-compose up -d
```
停止
```
docker-compose stop
```
イメージとコンテナ削除
```
docker image prune
docker container prune
```
