# vim: set noet :

.PHONY: clean all test release

MAVEN = mvn

clean:
	$(MAVEN) clean
	@rm -rf release
	@rm -rf target

all:: clean
	$(MAVEN) package

test: clean
	$(MAVEN) test

release:
	@rm -rf release
	@mkdir -p release/bin
	@mkdir -p release/static
	cp -rf bin release/
	cp -rf conf release/
	cp -rf ansinio_files release/
	cp -f target/Sharingan-1.0-SNAPSHOT.jar release/bin/Sharingan.jar
	cp -rf static/* release/static/
