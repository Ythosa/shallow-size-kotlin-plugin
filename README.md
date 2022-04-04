# An Kotlin compiler plugin template example

A small example of a Kotlin compiler plugin to calculate the sum of sized of 
the properties in data classes powered by [arrow-meta](https://github.com/arrow-kt/arrow-meta).

Tasks:
- [x] implement the plugin's body in the `AddShallowSizeMethod` plugin (replace all TODOs)
- [x] rewrite tests to parameterized tests in the `shalowSizePluginTest` module
- [x] add an GitHub action to build the plugin, run the tests and check the code quality

**Note**: Intellij Idea highlight shallowSize method in tests as an error, it is ok since the Kotlin compiler API with the old front-end requires implementing an additional Intellij Idea plugin to fix it. Just run tests and it will works correctly
