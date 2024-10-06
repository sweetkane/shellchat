# ShellChat
ShellChat is a fast peer-to-peer communication service for the command line.

### Technical Details
- Networking is implemented with gRPC and simple Protobuf definitions.
- Requires an exisiting port mapping from the machine's network gateway a local port. This is pretty inconvenient.
  - Possible workarounds:
    - use [jUPnP](https://github.com/jupnp/jupnp) library to programmatically add port mapping with UPnP
    - use [portmapper](https://github.com/offbynull/portmapper) library to programmaticallyadd port mapping with NAT-PMP
