# Awair Air Quality Monitor Application

## Introduction

This application is designed to gather data from an Awair device's local API. The Awair Element measures air quality in the house, providing data such as the Awair air quality score, temperature, relative humidity, carbon dioxide (CO2) levels, total volatile organic compounds (TVOCs), and particulate matter (PM2.5). This data is crucial for monitoring and improving indoor air quality.

The project consists of two main components:

1. **awair-api**: A backend service built with Micronaut, responsible for fetching and storing air quality data from the Awair device.
2. **awair-client**: A Vue.js frontend application that displays the air quality data in a user-friendly interface.

## awair-api

The `awair-api` module interacts with the Awair device to retrieve air quality data, including metrics like temperature, humidity, CO2 levels, and more. It provides a REST API for data access and includes scheduled tasks for regular data fetching.

For more details, see the [awair-api README](https://github.com/brunoflavio-com/awair/blob/main/awair-api/README.md).

## awair-client

The `awair-client` module is a Vue.js application that presents the data fetched by the `awair-api`. It offers a clean and intuitive interface for users to view and analyze air quality metrics.

For setup and development instructions, refer to the [awair-client README](https://github.com/brunoflavio-com/awair/blob/main/awair-client/README.md).

## Getting Started

Instructions for setting up the project, including prerequisites and steps to get the application running, can be found in the README files of the respective modules.

## Contributing

I welcome contributions if you find the project useful. Just get in touch and send in your pull request.
