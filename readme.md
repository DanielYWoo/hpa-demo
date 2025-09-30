# Overview

Setting up even a minimal Kubernetes environment with monitoring, collecting custom and external metrics, and experimenting with Horizontal Pod Autoscaling (HPA) is not trivial.
This article walks you through the process end to end using a lightweight local setup so you can understand how everything fits together—from core concepts to advanced usage.

If you're new to this: think of what follows as building a tiny (but realistic) “metrics → decisions → scaling” pipeline. You will:
1. Learn how time‑series data is structured (so later PromQL doesn’t look like wizardry).
2. See how Prometheus stores and queries your application metrics.
3. Expose a custom metric and watch Kubernetes scale on it.
4. Simulate an “external pressure” metric (a queue) and scale based on that too.
5. Read the raw metric APIs the HPA itself uses—no black boxes.

The goal: after finishing, you should be able to explain (with confidence) how a number scraped from an HTTP endpoint eventually causes new pods to appear.

Please download the source and follow [this article](https://danielw.cn/hpa-with-custom-and-external-metrics-in-k8s) to run the PoC 
