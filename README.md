# ShellChat
ShellChat is a fast peer-to-peer communication service for the command line.

![image](https://github.com/user-attachments/assets/d8979191-242d-41aa-afe3-e7920c2e65b3)

### Technical Details
- Networking is implemented with gRPC and simple Protobuf definitions.
- Requires an existing port mapping from the network gateway to a local port. This is pretty inconvenient.
  - Possible workarounds:
    - use [jUPnP](https://github.com/jupnp/jupnp) library to programmatically add port mapping with UPnP
    - use [portmapper](https://github.com/offbynull/portmapper) library to programmaticallyadd port mapping with NAT-PMP
