.PHONY: help
help:
	@awk -F':.*##' '/^[-_a-zA-Z0-9]+:.*##/{printf"%-12s\t%s\n",$$1,$$2}' $(MAKEFILE_LIST) | sort

.PHONY: get-datasets
get-datasets: ## Convert holidays_detailed.yml to edn
	# clojure -M:dev -m task.get-datasets run
	clj -M:dev dev/task/get_datasets.clj

.PHONY: format
format: ## Format files
	ag -g '\.clj(ces)?|edn$$' | xargs -t clojure -M:dev -m cljfmt.main fix
	npx prettier --write README.md

.PHONY: repl
repl: ## Start a REPL shell
	clj -M:dev -r
	# rlwrap -c -b "(){}[],^%$#@\"\";:''|\\" rebar3 clojerl repl

.PHONY: test
test: ## Test
	git ls-files | grep '\.clj\(ces\)\?\|edn$$' | xargs -t clojure -M:dev -m cljfmt.main check
	# clj -A:test
	# rebar3 clojerl test

.PHONY: upgrade
upgrade: ## Upgrade deps
	git submodule update --remote
	$(MAKE) get-datasets format
	# rebar3 upgrade
	# npx npm-check-updates -u
	# npm install
	# npm audit fix
	# npm fund
