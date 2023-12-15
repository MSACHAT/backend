
# MSACHAT/backend

MSACHAT/backend 是一个使用 Spring Boot、Spring Security 和 PostgreSQL 构建的聊天社区应用的后端。

## 目录

- [技术栈](#技术栈)
- [项目概述](#项目概述)
- [环境设置](#环境设置)
    - [依赖项](#依赖项)
    - [Docker Compose](#docker-compose)
- [如何运行](#如何运行)
- [如何使用 Spring Security](#如何使用-spring-security)
- [贡献](#贡献)
- [许可证](#许可证)

## 技术栈

MSACHAT/backend 使用以下技术栈：

- Spring Boot
- Spring Security
- PostgresSQL
- Docker Compose

## 项目概述

MSACHAT/backend 是一个简单的交流社区应用的后端，允许用户注册、登录和发送帖子。它使用Spring Boot提供后端API，并使用Spring Security来管理身份验证和授权，同时使用PostgresSQL来存储消息数据。

## 环境设置

在开始运行 MSACHAT/backend 之前，确保你已经设置了必要的环境。

### 依赖项

在本地开发环境中，你需要安装以下工具：

- Java Development Kit (JDK) 17 或更高版本
- Maven
- Docker

### Docker Compose

MSACHAT/backend 使用Docker Compose来启动一个包含PostgresSQL数据库的容器。确保你已经安装了Docker和Docker Compose。

## 如何运行

下面是运行 MSACHAT/backend 项目的步骤：

1. 使用以下命令克隆项目：

```bash
git clone https://github.com/MSACHAT/backend.git
```

2. 进入项目目录：

```bash
cd backend
```

3. 使用 Maven 构建项目：

```bash
mvn clean install
```

4. 使用 Docker Compose 启动 PostgresSQL 数据库：

```bash
docker-compose up -d
```

5. 启动 MSACHAT/backend Spring Boot 应用程序：

```bash
mvn spring-boot:run
```

应用程序现在应该已经启动并运行在 `http://localhost:8085`。

