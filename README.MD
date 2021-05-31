## 数据抓取工具
* 基于`Apache HttpComponents` 和 `Jsoup` 的网页数据抓取工具
* 通过配置来定义任务

### Sample
* 配置文件(`sample.yml`)
```
rod:
  common:
    output:
      only-console: true
  task-info:
    - task-name: spider-main
      urls-provider:
        urls:
          - "https://carllongj.github.io/"
      httpMethod: get
      selectors:
        title:
          - ".post-meta + h3 a"
```

* 运行任务 (`FishingRod.jar`,与`jar包同目录`)
```
java -jar FishingRod.jar sample.yml
```
