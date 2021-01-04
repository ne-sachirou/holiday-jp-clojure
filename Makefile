.PHONY: help
help:
	@awk -F':.*##' '/^[-_a-zA-Z0-9]+:.*##/{printf"%-12s\t%s\n",$$1,$$2}' $(MAKEFILE_LIST) | sort

.PHONY: get-datasets
get-datasets: ## Convert holidays_detailed.yml to edn
	clj -M:dev dev/task/get_datasets.clj

.PHONY: format
format: ## Format files
	ag -g '\.clj(ces)?|edn$$' | xargs -t clojure -M:dev -m cljfmt.main fix
	npx prettier --write README.md
	npx prettier --write .github/workflows/*.yml

.PHONY: publish
publish: ## Publish this to the package repository
	mvn deploy
	rebar3 hex publish || true

.PHONY: repl-clj
repl-clj: ## Start a REPL shell for Clojure
	clj -M:dev -r

.PHONY: repl-clje
repl-clje: ## Start a REPL shell for Clojerl
	rlwrap -c -b "(){}[],^%$#@\"\";:''|\\" rebar3 clojerl repl

.PHONY: repl-cljs
repl-cljs: ## Start a REPL shell for ClojureScript
	clojure -M:dev-cljs -m cljs.main -r

.PHONY: test-clj
test-clj:
	git ls-files | grep '\.clj\(ces\)\?\|edn$$' | xargs -t clojure -M:dev -m cljfmt.main check
	clojure -M:test

.PHONY: test-clje
test-clje:
	rebar3 clojerl test

.PHONY: test-cljr
test-cljr: ## Test for Clojure CLR. This works only on Windows
	exit 1

.PHONY: test-cljs
test-cljs:
	clojure -M:dev-cljs:test-cljs

.PHONY: test
test: test-clj test-clje test-cljs ## Test

.PHONY: upgrade
upgrade: ## Upgrade deps
	git submodule update --remote
	$(MAKE) get-datasets format
	clojure -Spom
	rebar3 upgrade
	rebar3 update
