# Profile

- application-profile.yml
- application-profile2.yml

spring.config.activate.on-profile 에 같은 profile을 사용해도 적용됨

```
02:19:58.196 [restartedMain] INFO  x.b.mvc.profile.Profile2Properties  - profile2 check : local2
02:19:58.196 [restartedMain] INFO  x.b.mvc.profile.Profile2Properties  - profile2 default check : default2
02:19:58.196 [restartedMain] INFO  x.b.mvc.profile.ProfileProperties  - profile check : local
02:19:58.196 [restartedMain] INFO  x.b.mvc.profile.ProfileProperties  - profile default check : default
```