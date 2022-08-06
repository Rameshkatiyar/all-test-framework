current_dir = $(shell pwd)
runner = testng.xml
class = none
group = regression
platform = all
env = local

test:
	mvn clean test -Dsurefire.suiteXmlFiles=${current_dir}/src/main/java/com/tech/base/${runner} -Dclass=${class} -Dgroup=${group} -Dplatform=${platform} -Denv=${env}
