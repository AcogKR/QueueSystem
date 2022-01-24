#QueueSystem

> 마인크래프트 대기열 시스템입니다.  
> 제한 인원의 범위를 벗어난 플레이어를 행동제약을 걸어 서버의 부하를 줄입니다.

###Command
> **/queue [on/off]** : 서버의 대기열 시스템을 활성화 합니다. :default: on  
> **/queue reload** : 플러그인 Config 를 리로드 합니다.

###Permission
> **queue.skip** 대기열을 무시하고 서버에 접속합니다.

###Config
```yaml
QueueSystem:
    max-players: 100 # 서버의 최대 플레이어 수
    queue-message-second: 10 # 대기열 메세지 출력 시간
```