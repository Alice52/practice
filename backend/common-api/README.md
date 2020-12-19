## notice

1. `import ResponseProperties.class` should not be used.
2. `HandlerInterceptorAdapter` cannot intercept 404.
3. `ResponseBodyAdvice` should do ignore `swagger-apis`
4. `@ExceptionHandler` without @ResponseBody will lead to loop handle.
5. `IBaseExceptionAssert#newException` can customize assert's message is show in response. 

## todo list

1. View **hutool** assert source code definition to extend `IBaseAssert`.
2. Need to research for specified response.
3. Do some research about http status without exception handler.