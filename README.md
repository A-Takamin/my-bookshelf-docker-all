# my-bookshelf-docker-all

my-bookshelfシリーズのfront, back, dbをすべてDocker化したリポジトリ。ローカル開発用。

1st release(2021/2/15)  
　・ログイン機能  
　・読了日、評価、感想を添えられる  
 
## 0. 目次
- [アプリ概要](https://github.com/A-Takamin/my-bookshelf-docker-all/blob/main/README.md#1-%E3%82%A2%E3%83%97%E3%83%AA%E6%A6%82%E8%A6%81)
- [ローカル環境構築手順](https://github.com/A-Takamin/my-bookshelf-docker-all#2-%E3%83%AD%E3%83%BC%E3%82%AB%E3%83%AB%E7%92%B0%E5%A2%83%E6%A7%8B%E7%AF%89%E6%89%8B%E9%A0%86)
- [アーキテクチャ](https://github.com/A-Takamin/my-bookshelf-docker-all#3-%E3%82%A2%E3%83%BC%E3%82%AD%E3%83%86%E3%82%AF%E3%83%81%E3%83%A3)
- [使用技術](https://github.com/A-Takamin/my-bookshelf-docker-all#4-%E4%BD%BF%E7%94%A8%E6%8A%80%E8%A1%93)
- [課題・ToDo](https://github.com/A-Takamin/my-bookshelf-docker-all#5-todo)
- [アプリイメージ（画像で紹介！）](https://github.com/A-Takamin/my-bookshelf-docker-all#6-%E3%82%A2%E3%83%97%E3%83%AA%E7%94%BB%E5%83%8F)


## 1. アプリ概要
読んだ本の感想などを保管できるアプリです。  
読んだ本を管理できたらいいな × いろんな技術をまとめて使いたいなと思い、自作しました。  
詳細については、このREADMEの一番下にアプリ内の画像を用意しています。

## 2. ローカル環境構築手順
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

## 3. アーキテクチャ
![アーキテクチャ](./ReadMeElements/arch.png "アーキテクチャ")

## 4. 使用技術
#### フロントエンド
- Vue.js
- Firebase
#### バックエンド
- Spring Boot
- PostgreSQL
#### インフラ（希望）
①ECS, Code Pipeline, Aurora  
②S3やCloudFrontをうまく使えるなら使う…？

## 5. ToDo
- ちゃんとTOC生成ツールを導入して内部リンクを貼る…（現在は力技で目次から飛ばしている）
- ログの出力
- おすすめ機能
- ユーザネーム導入
- 本IDとジャンル
- 内容をもっと充実させる（考える）


## 6. アプリ画像
デプロイがまだできないので、こちらの画像でご紹介します。
![トップ画面](./ReadMeElements/toppage.png "")  
![新規登録](./ReadMeElements/register.png "")  
![ログイン](./ReadMeElements/login.png "")  
![本棚](./ReadMeElements/home.png "")  
##### 本を追加  
![本追加1](./ReadMeElements/bookAdd.png "")  
![本追加2](./ReadMeElements/bookAdd2.png "")  
![本追加3](./ReadMeElements/bookSearch.png "")  
![本追加2](./ReadMeElements/bookAdd3.png "")  
##### 詳細画面  
![詳細](./ReadMeElements/bookAdd3.png "")  
